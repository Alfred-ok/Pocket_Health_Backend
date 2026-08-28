CREATE TABLE IF NOT EXISTS emergency_alert (
    alert_id UUID PRIMARY KEY,
    profile_id UUID NOT NULL REFERENCES profile(profile_id),
    alert_type VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'active',
    notes TEXT,
    location TEXT,
    recording_document_id UUID REFERENCES document(document_id),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    resolved_at TIMESTAMPTZ
);
